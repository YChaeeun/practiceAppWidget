package com.example.appwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent

class PracticeAppWidgetProvider : AppWidgetProvider() {

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }

    // configuration activity가 launch될 때는 호출이 안됨!
    // 그래서 이미 위젯이 설치되어 있는 상황에서 다시 빌드를 했을 때는 onUpdate()가 호출됨
    // 애초에 30분 이내는 pdatePeriodMillis 가 해당이 안된다고 함
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds?.forEach { id ->
//            val views = RemoteViews(context?.packageName, R.layout.layout_widget)
//            views.setTextViewText(R.id.txt_widget, "안녕하세욥")
//            appWidgetManager!!.partiallyUpdateAppWidget(id, views)

        }
    }


}