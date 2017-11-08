package de.shalama.simpledtg

import android.app.*
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import java.util.*

/**
 * Opens a AlertDialog where the user can set time and date.
 * The Fragment returns the selected time in ms back via activity result.
 * The value is accessibale via the key name 'value'
 * Created by shalama on 30.10.2017.
 */
class SimpleDateDlgFragment : DialogFragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    companion object {
        const val SIMPLE_DATE_RESULT_CODE = "value"
    }

    private var timeMs: Long = 0
    private lateinit var setDateTxt: TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {



        val dialogView = activity.layoutInflater.inflate(R.layout.date_picker_dialog, null) as View
        dialogView.findViewById<Button>(R.id.dateSetBtn).setOnClickListener({
                    DateFragment().show(childFragmentManager, "")
        })

        setDateTxt = dialogView.findViewById<TextView>(R.id.dateSetTxt)
        val returnIntent = Intent()
        return AlertDialog.Builder(activity, R.style.SimpleCalendarDlgStyle).setTitle("Pick Date").setView(dialogView).setPositiveButton("OK") { _, _ ->

            returnIntent.putExtra(SIMPLE_DATE_RESULT_CODE, timeMs)
            targetFragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, returnIntent)
            dialog.dismiss()

        }.setNegativeButton("CANCEL") { _, _ ->
            targetFragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, returnIntent)
            dialog.dismiss()

        }.create()


    }

    override fun onDateSet(p0: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        timeMs = GregorianCalendar(year, monthOfYear, dayOfMonth).timeInMillis
        TimePickerDialog(context, this, 12, 30, android.text.format.DateFormat.is24HourFormat(context)).show()

    }

    override fun onTimeSet(p0: TimePicker?, h: Int, m: Int) {
        timeMs += (m * 60 + h * 3600) * 1000
        val d = Date(timeMs)
        val date = android.text.format.DateFormat.getLongDateFormat(context).format(d)
        val time = android.text.format.DateFormat.getTimeFormat(context).format(d)
        setDateTxt.text = "$date  $time"

    }


}