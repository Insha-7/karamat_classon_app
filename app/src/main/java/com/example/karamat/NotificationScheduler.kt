package com.example.karamat

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.LocalTime.*
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.TemporalAdjusters

object NotificationScheduler {
    fun scheduleAll(context: Context, allItems: List<ItemTimeTable>) {
        for (item in allItems) {
            scheduleNotification(context, item)

        }
    }

    private fun scheduleNotification(context: Context, item: ItemTimeTable) {
        val now = ZonedDateTime.now()

        // find the date of the *next* occurrence of the given day
        var classDate = now.toLocalDate().with(java.time.temporal.TemporalAdjusters.nextOrSame(item.dayOfWeek))

        // build a ZonedDateTime for the class start
        var classDateTime = ZonedDateTime.of(classDate, item.startLocalTime, ZoneId.systemDefault())

        // subtract 30 minutes
        val notifyTime = classDateTime.minusMinutes(30)

        // skip if already passed
        if (notifyTime.isBefore(now)) return

        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("subject", item.subject)
            putExtra("location", item.location)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            item.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val triggerAtMillis = notifyTime.toInstant().toEpochMilli()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerAtMillis,
                    pendingIntent
                )
            } else {
                alarmManager.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerAtMillis,
                    pendingIntent
                )
            }
        } else {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                pendingIntent
            )
        }
        Log.d("NotificationScheduler", "Notification for ${item.subject} at $notifyTime")

    }



}