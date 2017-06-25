package cn.ucai.testdatabase;

import android.content.Context;

import java.util.List;
import java.util.Map;

/**
 * Created by clawpo on 2017/6/9.
 */

public class ProjectDao {
    private static final String TAG = "ProjectDao";
    ProjectDBManager dbMgr = null;
    public static final String GIFT_TABLE_NAME = "t_mifareultralight_project";
    public static final String GIFT_COLUMN_ID = "m_project_id";
    public static final String GIFT_COLUMN_NAME = "m_project_name";
    public static final String GIFT_COLUMN_TYPE = "m_project_type";
    public static final String GIFT_COLUMN_TIME = "m_project_time";

    public ProjectDao(Context context) {
        dbMgr = ProjectDBManager.getInstance(context);
    }

    public void setProjectList(List<Project> list){
        dbMgr.saveProjectList(list);
    }

    public Map<Integer, Project> getGiftList(){
        return dbMgr.getProjectList();
    }

    public Project getProjectById(int id){
        return dbMgr.getProject(id);
    }

    public Project getProjectByName(String name){
        return dbMgr.getProject(name);
    }

    public long saveProject(Project project){
        return dbMgr.saveProject(project);
    }
}
