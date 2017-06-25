package cn.ucai.testdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_info)
    EditText mEtInfo;
    @BindView(R.id.btn_write)
    Button mBtnWrite;
    @BindView(R.id.btn_read)
    Button mBtnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void save(View view) {
        String name = mEtInfo.getText().toString().trim();
        Project project = new Project(name);
        ProjectDao dao = new ProjectDao(MainActivity.this);
        dao.saveProject(project);
    }

    public void read(View view) {
        String name = mEtInfo.getText().toString().trim();
        ProjectDao dao = new ProjectDao(MainActivity.this);
        Project projectByName = dao.getProjectByName(name);
        String ed = projectByName.toString();
        mEtInfo.setText(ed);
    }
}
