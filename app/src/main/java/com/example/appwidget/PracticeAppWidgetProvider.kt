package com.example.appwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class PracticeAppWidgetProvider : AppWidgetProvider() {

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds!!.forEach { id ->

            val pendingIntent = Intent(context, ExampleActivity::class.java).let { intent ->
                PendingIntent.getActivity(context, 0, intent, 0)
            }


            val view = RemoteViews(context?.packageName, R.layout.layout_widget).apply {
                setOnClickPendingIntent(R.id.img_1, pendingIntent)
            }

            appWidgetManager!!.updateAppWidget(id, view)

        }
    }


}