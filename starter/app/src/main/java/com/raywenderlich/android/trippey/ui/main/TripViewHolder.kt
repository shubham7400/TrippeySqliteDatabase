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

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raywenderlich.android.trippey.R
import com.raywenderlich.android.trippey.model.Trip
import kotlinx.android.synthetic.main.item_trip.view.*

class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun showData(trip: Trip, onItemLongTap: (Trip) -> Unit, onItemTapped: (Trip) -> Unit) = with(itemView) {
    tripName.text = trip.title
    tripDescription.text = trip.details

    val imageToLoad = when {
      trip.imageUrl != null -> trip.imageUrl
      trip.locations.any { it.locationImageUrl != null } ->
        trip.locations.first { it.locationImageUrl != null }.locationImageUrl
      else -> ""
    }

    if (imageToLoad.isNullOrBlank()) {
      tripImage.setImageResource(R.drawable.placeholder_image)
    } else {
      Glide.with(itemView).load(imageToLoad).into(tripImage)
    }

    setOnLongClickListener {
      onItemLongTap(trip)
      true
    }

    setOnClickListener {
      onItemTapped(trip)
    }
  }
}