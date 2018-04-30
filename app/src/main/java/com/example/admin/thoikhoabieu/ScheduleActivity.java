package com.example.admin.thoikhoabieu;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleActivity extends AppCompatActivity {

    TextView txtTKb, txtTuan, txtTiet1, txtSoTiet, txtTiet2, txtTiet3, txtTiet4;
    TextView txtThu2, txtThu3, txtThu4, txtThu5, txtThu6, txtThu7;
    GridView gvTimeTable;
    ArrayList<InforTimeTable> listDS;
    CustomGridAdapter gridAdapter;

    Spinner spinner;
    ArrayAdapter<String> adapterSpinnerDanhMuc;
    Map<String, Week> WeekList = new HashMap<String, Week>();

    String stringStartHK = "08-04-2018";
    String stringEndHK = "05-05-2018";
    Date dateStartHK;
    Date dateEndHK;
    SimpleDateFormat formatter;
    List<String> keys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        anhXa();

        formatter = new SimpleDateFormat("dd-MM-yyyy");
        initSpinner();
        spinner.setOnItemSelectedListener(new MyEvent());


        gridAdapter = new CustomGridAdapter(this, R.layout.list_item_layput, listDS);
        gvTimeTable.setAdapter(gridAdapter);

        gvTimeTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                dialogDetail();
            }
        });
    }

    private void initSpinner() {

        try {

            dateStartHK = formatter.parse(stringStartHK);
            dateEndHK = formatter.parse(stringEndHK);

            getWeeksBetween(dateStartHK, dateEndHK);

        } catch (ParseException e) {
            e.printStackTrace();
        }

//        List<String> keys = new ArrayList<>();
//        for (Map.Entry me : WeekList.entrySet()) {
//            keys.add(me.getKey().toString());
//        }

        adapterSpinnerDanhMuc = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, keys);

        //xu ly SpinnerDanhMuc
        adapterSpinnerDanhMuc.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        //thiết lập adapter cho Spinner
        spinner.setAdapter(adapterSpinnerDanhMuc);
    }


    //hop thoai dialog hien ra
    private void dialogDetail() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_detail);
        dialog.show();
    }

    private void anhXa() {

        spinner = findViewById(R.id.spinner);

        txtTKb = findViewById(R.id.txtTKb);
        txtTuan = findViewById(R.id.txtTuanDetail);
        txtSoTiet = findViewById(R.id.txtSoTiet);
        txtTiet1 = findViewById(R.id.txtTiet1);
        txtTiet2 = findViewById(R.id.txtTiet2);
        txtTiet3 = findViewById(R.id.txtTiet3);
        txtTiet4 = findViewById(R.id.txtTiet4);

        txtThu2 = findViewById(R.id.txtThu2);
        txtThu3 = findViewById(R.id.txtThu3);
        txtThu4 = findViewById(R.id.txtThu4);
        txtThu5 = findViewById(R.id.txtThu5);
        txtThu6 = findViewById(R.id.txtThu6);
        txtThu7 = findViewById(R.id.txtThu7);

        gvTimeTable = findViewById(R.id.gridView);
        listDS = new ArrayList<>();

        listDS.add(new InforTimeTable("", "", " "));
        listDS.add(new InforTimeTable("", "Tiết 1", " "));
        listDS.add(new InforTimeTable("", "Tiết 2", " "));
        listDS.add(new InforTimeTable("", "Mon", " "));
        listDS.add(new InforTimeTable("Game", "Phòng: ", "1A12"));
        listDS.add(new InforTimeTable("", "", " "));
        listDS.add(new InforTimeTable("", "Tue", " "));
        listDS.add(new InforTimeTable("", "", " "));
        listDS.add(new InforTimeTable("Di động", "Phòng: ", "1A12"));
    }

    public int getWeeksBetween(Date a, Date b) {

        if (b.before(a)) {
            return -getWeeksBetween(b, a);
        }
        a = resetTime(a);
        b = resetTime(b);

        Calendar cal = new GregorianCalendar();
        cal.setTime(a);
        int weeks = 0;

        String start = null;
        String end = null;
        while (cal.getTime().before(b)) {
            // add another week
            start = formatter.format(cal.getTime());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cal.getTime());
            calendar.add(Calendar.DATE, + 6);
            end = formatter.format(calendar.getTime());

            WeekList.put("Tuần " + (weeks + 1), new Week(start, end));
            keys.add("Tuần " + (weeks + 1));

            cal.add(Calendar.WEEK_OF_YEAR, 1);
            weeks++;

        }
        return weeks;
    }

    public static Date resetTime(Date d) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private class MyEvent implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            //xử lý ở đây, chọn vào Tuần, sẽ get dc ngày bắt đầu và ngày kết thúc gửi về server
            Log.d("DD", WeekList.get(keys.get(i)).toString());
            txtTuan.setText("Tuần " + (i + 1) + ": " + WeekList.get(keys.get(i)).toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

}
