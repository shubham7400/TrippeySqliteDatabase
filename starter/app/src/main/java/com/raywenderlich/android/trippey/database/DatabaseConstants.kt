package com.raywenderlich.android.trippey.database

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
object DatabaseConstants {

  const val DATABASE_NAME = "Trippey"
  const val DATABASE_VERSION = 2

  /**
   * Table names and column names for the database model.
   */

  const val TRIP_TABLE_NAME = "trips"
  const val COLUMN_ID = "id"
  const val COLUMN_TITLE = "title"
  const val COLUMN_COUNTRY = "country"
  const val COLUMN_DETAILS = "details"
  const val COLUMN_IMAGE_URL = "imageUrl"
  const val COLUMN_LOCATIONS = "locations"

  /**
   * Queries to help out with database setup.
   * */

  const val SQL_CREATE_ENTRIES = """
    CREATE TABLE $TRIP_TABLE_NAME
    ($COLUMN_ID TEXT PRIMARY KEY,
     $COLUMN_TITLE TEXT NOT NULL,
     $COLUMN_COUNTRY TEXT NOT NULL DEFAULT '',
     $COLUMN_DETAILS TEXT NOT NULL,
     $COLUMN_IMAGE_URL TEXT,
     $COLUMN_LOCATIONS TEXT NOT NULL DEFAULT '')
  """

  const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TRIP_TABLE_NAME"

  const val SQL_UPDATE_DATABASE_ADD_LOCATION = "ALTER TABLE $TRIP_TABLE_NAME ADD $COLUMN_LOCATIONS TEXT NOT NULL DEFAULT '' "

  const val QUERY_BY_ID = "$COLUMN_ID LIKE ?"
}