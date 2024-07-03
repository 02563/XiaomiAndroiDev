package com.example.androidui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private String current = "";
    private String star = "*"; // 用于替换数字的字符

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        //textView.setText("Changed Text");
        //textView.setText(getResources().getString(R.string.text_change));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,40);
        textView.setTextColor(getResources().getColor(R.color.purple_500));
        textView.setTypeface(null, Typeface.BOLD_ITALIC);//设置文字样式
        textView.setSelected(true);//使跑马灯生效

        // 获取Button的实例
        Button button = findViewById(R.id.button);
        Button buttoncommit = findViewById(R.id.commit);

        //获取EditText实例
        EditText editText = findViewById(R.id.myEditText);
        TextView richtext = findViewById(R.id.richtext);

        // 设置长按事件监听器
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // 处理长按事件
                Toast.makeText(MainActivity.this, "Button Long Clicked", Toast.LENGTH_SHORT).show();
                // 返回true表示已经处理了该事件，不希望事件继续传递
                return true;
            }
        });

        button.setOnTouchListener((v, event) -> {
            Toast.makeText(MainActivity.this,"Button touched"+event.getAction(),Toast.LENGTH_SHORT).show();
            return false;
        });

        RadioGroup radioGroupGender = findViewById(R.id.radioGroup);

        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 检查哪个RadioButton被选中
                if (checkedId == R.id.radioMale) {
                    // 男被选中
                    // 处理逻辑
                    Toast.makeText(MainActivity.this, "Choose man", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radioFemale) {
                    // 女被选中
                    // 处理逻辑
                    Toast.makeText(MainActivity.this, "Choose woman", Toast.LENGTH_SHORT).show();
                }
            }
        });

       //buttoncommit.setOnClickListener(new View.OnClickListener() {
       //    @Override
       //    public void onClick(View v) {
       //        // Get the content of the EditText
       //        String editTextContent = editText.getText().toString();
       //        Toast.makeText(MainActivity.this, editTextContent, Toast.LENGTH_SHORT).show();
       //
       //    }
       //});

        //设置EditText输入限制
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //// 获取当前光标位置
                //int cursorPosition = editText.getSelectionStart();
//
                //// 检查最后一个输入的字符是否是允许的字符
                //if (cursorPosition > 0 && !Character.toString(s.charAt(cursorPosition - 1)).matches("^[A-Z0-9*]+$")) {
                //    // 显示提示
                //    Toast.makeText(MainActivity.this,"删除非法字符"+s.charAt(s.length()-1),Toast.LENGTH_SHORT).show();
//
                //    // 从 Editable 对象中删除最后一个字符
                //    Editable editable = editText.getText();
                //    editable.delete(cursorPosition - 1, cursorPosition);
//
                //    // 更新 EditText 的文本和光标位置
                //    editText.setText(editable);
                //    editText.setSelection(cursorPosition - 1);
                //} else if (Character.toString(s.charAt(cursorPosition - 1)).matches("^[0-9*]+$")) {
                //    Editable editable = editText.getText();
                //    editable.replace(s.length() - 1, s.length(), "*", 0, 1);
                //    // 更新EditText的文本
                //    editText.setText(editable);
                //    editText.setSelection(cursorPosition);
                //}


                // 获取当前光标位置
                //int cursorPosition = editText.getSelectionStart();
//
                //// 先保存当前文本状态
                //Editable editable = editText.getText();
//
                //// 判断是否需要修改文本
                //if (cursorPosition > 0 && !Character.toString(s.charAt(cursorPosition - 1)).matches("^[A-Z0-9*]+$")) {
                //    // 显示提示
                //    Toast.makeText(MainActivity.this, "删除非字符 " + s.charAt(cursorPosition - 1), Toast.LENGTH_SHORT).show();
//
                //    // 删除最后一个字符
                //    editable.delete(cursorPosition - 1, cursorPosition);
//
                //    // 更新 EditText 的文本和光标位置
                //    editText.setText(editable);
                //    editText.setSelection(cursorPosition - 1);
                //}

            }


            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        // 创建SpannableString对象
        SpannableString spannableString = new SpannableString("这是文本A ![图片] 这是文本B");

        // 设置字体和大小
        spannableString.setSpan(new TypefaceSpan("serif"), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(0.5f), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置背景颜色
        spannableString.setSpan(new BackgroundColorSpan(Color.YELLOW), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置图片
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.celeste); // 替换为你的图片资源
        // 设定想要缩小到的尺寸（例如，宽度缩小到原来的50%，高度也缩小到原来的50%）
        int newWidth = 250;
        int newHeight = 250;
//
        // 缩放Bitmap
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);

        ImageSpan imageSpan = new ImageSpan(this, scaledBitmap, ImageSpan.ALIGN_BASELINE);
        spannableString.setSpan(imageSpan, 6, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
        // 设置另一种字体和大小
        //spannableString.setSpan(new TypefaceSpan("monospace"), 7, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //spannableString.setSpan(new RelativeSizeSpan(1.2f), 7, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
        // 设置另一种背景颜色
        spannableString.setSpan(new BackgroundColorSpan(Color.GREEN), 11, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
        // 应用富文本到EditText
        richtext.setText(spannableString);
    }



    public void onButtonClick(View view) {
        // 处理点击事件
        Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
    }

    public void commitbutton(View view) {
        // 处理点击事件
        EditText editText = findViewById(R.id.myEditText);
        String editTextContent = editText.getText().toString();
        Toast.makeText(MainActivity.this, editTextContent, Toast.LENGTH_SHORT).show();
    }

    public void goToNextActivity(View view) {
        // 创建Intent来启动NextActivity
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        // 启动NextActivity
        startActivity(intent);
    }
}