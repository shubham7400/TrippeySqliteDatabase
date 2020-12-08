/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.trippey.ui.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.android.trippey.App
import com.raywenderlich.android.trippey.R
import com.raywenderlich.android.trippey.model.SortOption
import com.raywenderlich.android.trippey.model.Trip
import com.raywenderlich.android.trippey.model.getSortOptionFromName
import com.raywenderlich.android.trippey.repository.TrippeyRepositoryImpl.Companion.KEY_SORT_OPTION
import com.raywenderlich.android.trippey.ui.addTrip.AddTripActivity
import com.raywenderlich.android.trippey.ui.main.sorting.SortOptionDialog
import com.raywenderlich.android.trippey.ui.tripDetails.TripDetailsActivity
import com.raywenderlich.android.trippey.utils.createAndShowDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private val adapter by lazy { TripAdapter(::onItemLongTapped, ::onItemTapped) }
  private val repository by lazy { App.repository }

  private val localPreferences by lazy { getPreferences(Context.MODE_PRIVATE) }

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initUi()
  }

  private fun initUi() {
    tripsList.adapter = adapter
    tripsList.layoutManager = LinearLayoutManager(this)

    addTrip.setOnClickListener {
      startActivity(AddTripActivity.getIntent(this))
    }

    filterOptions.setOnClickListener {
      showFilterAndSortingDialog()
    }
  }

  private fun showFilterAndSortingDialog() {
    val dialog = SortOptionDialog { sortOption ->
      saveSortOption(sortOption)
      refreshData()
    }
    dialog.show(supportFragmentManager, null)
  }

  private fun refreshData() {
    adapter.setData(repository.getTrips(), getSortOption())
  }

  private fun getSortOption(): SortOption {
    return getSortOptionFromName(localPreferences.getString(KEY_SORT_OPTION, "") ?: "")
  }

  private fun saveSortOption(sortOption: SortOption) {
    localPreferences.edit()
      .putString(KEY_SORT_OPTION, sortOption.name)
      .apply()
  }

  override fun onResume() {
    super.onResume()

    refreshData()
  }

  private fun onItemLongTapped(trip: Trip) {
    createAndShowDialog(this,
      getString(R.string.delete_title),
      getString(R.string.delete_message, trip.title),
      onPositiveAction = {
        repository.deleteTrip(trip.id)

        refreshData()
      })
  }


  private fun onItemTapped(trip: Trip) {
    startActivity(TripDetailsActivity.getIntent(this, trip))
  }
}
