package com.syntaxsolutions.azkarcalculator.view.activityInteractor;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Update;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.database.QathmDetailsTable;
import com.syntaxsolutions.azkarcalculator.base.database.QathmHeaderTable;
import com.syntaxsolutions.azkarcalculator.base.database.QathmHeaderTable$Table;
import com.syntaxsolutions.azkarcalculator.dto.QathmHeaderDto;
import com.syntaxsolutions.azkarcalculator.view.activity.QathmDetailsActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;

/**
 * Created by lenovo on 16-01-2017.
 */

public class QathmActivityInteractor extends Observable{

    Activity activity;

    public QathmActivityInteractor(Activity activity) {
        this.activity = activity;
    }

    public void proceedNavigation(QathmHeaderTable qathmHeaderTable) {
        Intent intent = new Intent(activity, QathmDetailsActivity.class);
        QathmHeaderDto qathmHeaderDto = new QathmHeaderDto();
        qathmHeaderDto.setId(qathmHeaderTable.getId());
        qathmHeaderDto.setName(qathmHeaderTable.getQathmName());
        qathmHeaderDto.setStartDate(qathmHeaderTable.getStartDate());
        qathmHeaderDto.setEnddate(qathmHeaderTable.getEndDate());
        qathmHeaderDto.setSurath(qathmHeaderTable.getSurathName());
        qathmHeaderDto.setAyath(qathmHeaderTable.getAyathNo());

        Gson gson = new Gson();
        String json = gson.toJson(qathmHeaderDto);
        intent.putExtra("navigationData", json);
        activity.startActivity(intent);

    }

    public void showPopUpForEdit(final QathmHeaderTable selectedHeaderTableForEdit) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View promptView = layoutInflater.inflate(R.layout.popup_edit_qathm, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setView(promptView);

        final EditText edtSurathName = (EditText) promptView.findViewById(R.id.edtQathm_surath_nameVal);
        final EditText edtAyathNo = (EditText) promptView.findViewById(R.id.edtQathmAyathNoVal);
        edtSurathName.setText(selectedHeaderTableForEdit.getSurathName());
        edtAyathNo.setText("" + selectedHeaderTableForEdit.getAyathNo());
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        updateQathmHeaderTable(edtSurathName.getText().toString(), edtAyathNo.getText().toString(), selectedHeaderTableForEdit.getId());
                        saveQuathmDetails(edtSurathName.getText().toString(), edtAyathNo.getText().toString(), selectedHeaderTableForEdit.getId());
                        setChanged();
                        notifyObservers("success");
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                setChanged();
                                notifyObservers("cancel");
                                dialog.cancel();
                            }
                        });
        alertDialogBuilder.setTitle("Edit Qathm");
        AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }

    private void updateQathmHeaderTable(String surathName, String ayathNo, long id) {
        QathmHeaderTable qathmHeaderTable = new QathmHeaderTable();
        qathmHeaderTable.setSurathName(surathName);
        qathmHeaderTable.setAyathNo(Long.parseLong(ayathNo));
        Where update = new Update<>(QathmHeaderTable.class).
                set(Condition.column(QathmHeaderTable$Table.SURATHNAME).eq(qathmHeaderTable.getSurathName()),
                        Condition.column(QathmHeaderTable$Table.AYATHNO).eq(qathmHeaderTable.getAyathNo()))
                .where(Condition.column(QathmHeaderTable$Table.ID).is(id));
        update.queryClose();
    }

    private void saveQuathmDetails(final String surath, final String ayath, final long id) {
        QathmDetailsTable qathmDetailsTable = new QathmDetailsTable();
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        Date currentDate = new Date();
        Calendar calander = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        qathmDetailsTable.setHeaderId(id);
        qathmDetailsTable.setSurath(surath);
        qathmDetailsTable.setAyath(ayath);
        qathmDetailsTable.setDate(simpleDateFormat.format(currentDate));
        qathmDetailsTable.setTime(simpleTimeFormat.format(calander.getTime()));
        qathmDetailsTable.save();
    }
}
