package com.example.appwidget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_configuration.*

class ConfigurationActivity : AppCompatActivity() {

    lateinit var appWidgetManager: AppWidgetManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setResult(Activity.RESULT_CANCELED)
        setContentView(R.layout.activity_configuration)

        appWidgetManager = AppWidgetManager.getInstance(this@ConfigurationActivity)

        val widgetId = getWidgetId()
        if (widgetId == AppWidgetManager.INVALID_APPWIDGET_ID) finish()

        setConfigValuesAndUpdateView(this@ConfigurationActivity, widgetId)

        goToWidget(widgetId)

    }

    private fun getWidgetId(): Int {
        return intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID
    }

    private fun setConfigValuesAndUpdateView(context: Context, appWidgetId: Int) {
        RemoteViews(context.packageName, R.layout.layout_widget).also {
            setConfigurationValue(it)
            appWidgetManager.updateAppWidget(appWidgetId, it)
        }
    }

    private fun setConfigurationValue(view: RemoteViews) {
        view.apply {
            setTextViewText(R.id.txt_widget, "야호~~~")
            setImageViewResource(R.id.img_widget, R.drawable.t4)
        }
    }

    private fun goToWidget(appWidgetId: Int) {
        btn_submit.setOnClickListener {
            val result = Intent().apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            }
            setResult(Activity.RESULT_OK, result)

            finish()
        }
    }
}
