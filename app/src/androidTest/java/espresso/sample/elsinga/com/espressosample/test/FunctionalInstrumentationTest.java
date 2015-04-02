/**
 * Copyright 2015. Wiebe Elsinga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package espresso.sample.elsinga.com.espressosample.test;

import android.test.ActivityInstrumentationTestCase2;

import espresso.sample.elsinga.com.espressosample.MainActivity;
import espresso.sample.elsinga.com.espressosample.R;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

public class FunctionalInstrumentationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public FunctionalInstrumentationTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testAnswer_to_the_Ultimate_Question_of_Life_the_Universe_and_Everything() {
        onView(withText("7")).perform(click());
        onView(withText("×")).perform(click());
        onView(withText("6")).perform(click());
        onView(withText("=")).perform(click());

        onView(withId(R.id.resText)).check(matches(withText("42")));
    }
}