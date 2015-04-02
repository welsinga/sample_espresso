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

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import espresso.sample.elsinga.com.espressosample.MainActivity;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TechnicalTest {

    MainActivity mMainActivity;

    @Before
    public void setUp() throws Exception {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                mMainActivity = new MainActivity();
            }
        });
    }

    @Test
    public void answer_to_the_Ultimate_Question_of_Life_the_Universe_and_Everything() {
        assertEquals(42.0, mMainActivity.calculate("7×6"));
    }

}