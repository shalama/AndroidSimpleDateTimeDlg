package de.shalama.simpledtg

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import java.util.*

/**
 * Created by shalama on 05.11.17.
 */

class DateFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      val c = Calendar.getInstance()


        return DatePickerDialog(activity,  parentFragment as DatePickerDialog.OnDateSetListener, c.get(Calendar.YEAR),  c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
    }



}