/**
 * Copyright 2015. Wiebe Elsinga
 * *
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
package espresso.sample.elsinga.com.espressosample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Vector;


public class MainActivity extends Activity {

    private EditText editText = null;

    private NumBtnClickListener numBtnClickListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numBtnClickListener = new NumBtnClickListener();
        editText = (EditText) findViewById(R.id.resText);

        initBtn();
    }

    private void initBtn() {
        findViewById(R.id.num_0).setOnClickListener(numBtnClickListener);
        findViewById(R.id.num_1).setOnClickListener(numBtnClickListener);
        findViewById(R.id.num_2).setOnClickListener(numBtnClickListener);
        findViewById(R.id.num_3).setOnClickListener(numBtnClickListener);
        findViewById(R.id.num_4).setOnClickListener(numBtnClickListener);
        findViewById(R.id.num_5).setOnClickListener(numBtnClickListener);
        findViewById(R.id.num_6).setOnClickListener(numBtnClickListener);
        findViewById(R.id.num_7).setOnClickListener(numBtnClickListener);
        findViewById(R.id.num_8).setOnClickListener(numBtnClickListener);
        findViewById(R.id.num_9).setOnClickListener(numBtnClickListener);

        findViewById(R.id.point).setOnClickListener(numBtnClickListener);
        findViewById(R.id.op_add).setOnClickListener(numBtnClickListener);
        findViewById(R.id.op_div).setOnClickListener(numBtnClickListener);
        findViewById(R.id.op_mul).setOnClickListener(numBtnClickListener);
        findViewById(R.id.op_sub).setOnClickListener(numBtnClickListener);

        findViewById(R.id.op_equ).setOnClickListener(new EqualBtnClickListener());

        findViewById(R.id.clear).setOnClickListener(new ClearBtnClickListener());

    }

    public class NumBtnClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            String string = editText.getText().toString() + btn.getText().toString();
            editText.setText(string);
            editText.setSelection(string.length());
        }
    }

    public class ClearBtnClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            editText.getText().clear();
        }
    }

    public class EqualBtnClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            String string = editText.getText().toString();

            if (string.equals("")) {
//                MainActivity.this.runOnUiThread(new Runnable() {
//                    public void run() {
//                        Toast.makeText(MainActivity.this, " No value given", Toast.LENGTH_LONG).show();
//                    }
//                });
                return;
            }
            try {
                Double result = calculate(string);

                String resultString = "";
                if (result % 1.0 == 0.0) {
                    resultString = result.longValue() + "";
                } else {
                    resultString = result + "";
                }
                editText.setText(resultString);
                editText.setSelection(resultString.length());
            } catch (RuntimeException rte) {
//                MainActivity.this.runOnUiThread(new Runnable() {
//                    public void run() {
//                        Toast.makeText(MainActivity.this, " Can't calculate", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        }

    }

    public Double calculate(String calcString) throws RuntimeException {
        String[] numberStrings = calcString.split("[\\+\\-\\×\\÷]");

        Vector<String> operators = new Vector<String>();
        Vector<Double> numbersVector = new Vector<Double>();
        for (String string : numberStrings) {
            numbersVector.add(Double.parseDouble(string));
        }

        for (char object : calcString.toCharArray()) {
            if (object == '+' || object == '-' || object == '×' || object == '÷') {
                operators.add(Character.toString(object));
            }
        }

        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals("×")) {
                numbersVector.set(i, numbersVector.get(i) * numbersVector.get(i + 1));
                numbersVector.remove(i + 1);
                operators.remove(i);
                i--;
            } else if (operators.get(i).equals("÷")) {
                numbersVector.set(i, numbersVector.get(i) / numbersVector.get(i + 1));
                numbersVector.remove(i + 1);
                operators.remove(i);
                i--;
            }

        }

        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals("+")) {
                numbersVector.set(i, numbersVector.get(i) + numbersVector.get(i + 1));
                numbersVector.remove(i + 1);
                operators.remove(i);
                i--;
            } else if (operators.get(i).equals("-")) {
                numbersVector.set(i, numbersVector.get(i) - numbersVector.get(i + 1));
                numbersVector.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }
        return numbersVector.get(0);
    }
}
