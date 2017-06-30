package com.syntaxsolutions.azkarcalculator.view.activityInteractor;

/**
 * Created by lenovo on 14-01-2017.
 */

public interface ActivityIntractable {

    void proceedSave(final String name, final String startDate, final String endDate, final String target);

    StringBuilder save(final String name, final String startDate, final String endDate, final String target);

    boolean validate(final String name, final String startDate, final String endDate, final String target, StringBuilder responseMessage);

    interface QathmCreationInteractrable {

        StringBuilder save(final String name, final String startDate, final String endDate);

        boolean validate(final String name, final String startDate, final String endDate, StringBuilder responseMessage);

        void proceedSave(final String name, final String startDate, final String endDate);


    }

}
