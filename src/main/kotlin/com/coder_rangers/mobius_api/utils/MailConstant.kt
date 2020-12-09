package com.coder_rangers.mobius_api.utils

object MailConstant {
    object Template {
        const val PATIENT_TEST_RESULTS_HTML = "patient-test-results.html"
        const val PATIENT_WEEKLY_REPORT_HTML = "weekly-report.html"
    }

    object Subject {
        const val PATIENT_TEST_RESULTS = "Resultados del test de su paciente"
        const val PATIENT_WEEKLY_REPORT = "Reporte semanal de su paciente"
    }

    object Attachment {
        const val PATIENT_TEST_RESULTS_PDF = "resultados-del-test.pdf"
        const val PATIENT_WEEKLY_REPORT_PDF = "reporte-semanal.pdf"
    }
}
