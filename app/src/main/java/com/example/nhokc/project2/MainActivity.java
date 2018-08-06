package com.example.nhokc.project2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.nhokc.project2.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,JobRVAdapter.IJob{
    private ActivityMainBinding binding;
    private List<Job> jobs = new ArrayList<>();
    private Calendar cal;
    private Date dateFinish;
    private Date timeFinish;
    private JobRVAdapter adapter = new JobRVAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        getDefaultInfor();
        initialize();
    }

    private void initialize() {
        initializeRV();
        binding.btnSetDate.setOnClickListener(this);
        binding.btnSetTime.setOnClickListener(this);
        binding.btnThem.setOnClickListener(this);
    }
    private void initializeRV(){
        adapter.setIdataTimer(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvJob.setLayoutManager(linearLayoutManager);
        binding.rvJob.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_set_date:
                showDatePickerDialog();
                break;
            case R.id.btn_set_time:
                showTimePickerDialog();
                break;
            case R.id.btn_them:
                String title= binding.edtCongViec.getText().toString();
                String content = binding.edtNoiDung.getText().toString();
                jobs.add(0,new Job(title,content,dateFinish,timeFinish));
                adapter.notifyDataSetChanged();
                adapter.addItem(0);
                break;
        }
    }
    public void getDefaultInfor()
    {
        cal=Calendar.getInstance();
        SimpleDateFormat dft=null;
        dft=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate=dft.format(cal.getTime());
        binding.txtDate.setText(strDate);
        dft=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String strTime=dft.format(cal.getTime());
        binding.txtTime.setText(strTime);
        dft=new SimpleDateFormat("HH:mm",Locale.getDefault());
        binding.txtTime.setTag(dft.format(cal.getTime()));
        binding.edtCongViec.requestFocus();
        dateFinish=cal.getTime();
        timeFinish=cal.getTime();
    }
    public void showDatePickerDialog()
    {
        DatePickerDialog.OnDateSetListener callback=new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                binding.txtDate.setText(
                        (dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                cal.set(year, monthOfYear, dayOfMonth);
                dateFinish=cal.getTime();
            }
        };

        String s=binding.txtDate.getText()+"";
        String strArrtmp[]=s.split("/");
        int ngay=Integer.parseInt(strArrtmp[0]);
        int thang=Integer.parseInt(strArrtmp[1])-1;
        int nam=Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic=new DatePickerDialog(
                MainActivity.this,android.R.style.Theme_Holo_Light_Dialog,
                callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày hoàn thành");
        pic.show();
    }
    public void showTimePickerDialog()
    {
        TimePickerDialog.OnTimeSetListener callback=new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view,
                                  int hourOfDay, int minute) {
                String s=hourOfDay +":"+minute;
                int hourTam=hourOfDay;
                if(hourTam>12)
                    hourTam=hourTam-12;
                binding.txtTime.setText
                        (hourTam +":"+minute +(hourOfDay>12?" CH":" SA"));
                binding.txtTime.setTag(s);
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                timeFinish=cal.getTime();
            }
        };

        String s=binding.txtTime.getTag()+"";
        String strArr[]=s.split(":");
        int gio=Integer.parseInt(strArr[0]);
        int phut=Integer.parseInt(strArr[1]);
        TimePickerDialog time=new TimePickerDialog(
                MainActivity.this,android.R.style.Theme_Holo_Light_Dialog,
                callback, gio, phut, true);
        time.setTitle("Chọn giờ hoàn thành");
        time.show();
    }

    @Override
    public int getCount() {
        return jobs.size();
    }

    @Override
    public Job getJob(int position) {
        return jobs.get(position);
    }
}
