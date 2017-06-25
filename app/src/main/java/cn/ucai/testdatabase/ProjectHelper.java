package cn.ucai.testdatabase;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.Map;

/**
 * Created by clawpo on 2017/6/16.
 */

public class ProjectHelper {
    private static final String TAG = "ProjectHelper";
    private Map<Integer,Project> projectMap;
    private List<Project> projectList;
    private ProjectDao projectDao;
    private static ProjectHelper instance = null;

    public synchronized static ProjectHelper getInstance(){
        if (instance==null){
            instance = new ProjectHelper();
        }
        return instance;
    }

    public ProjectHelper() {
    }

    public void init(Context context){
        projectDao = new ProjectDao(context);
    }

    public String getData(String idString){
        Log.e(TAG,"getData,id="+idString);
        int id = getHexString2int(idString);
        Project project = projectDao.getProjectById(id);
//        return projectDao.getProjectById(getHexString2int(id)).getName();
        return project!=null?project.getName():"";
    }

    public int saveProjectInfo(String name){
        Project project = projectDao.getProjectByName(name);
        if (project!=null){
            return project.getId();
        }else{
            Project pro = new Project(name);
            return (int) projectDao.saveProject(pro);
        }
    }

    public String[] getDataFromDB(String[] data){
        Log.e(TAG,"getDataFromDB,data.length="+data.length);
        String[] resultData = null;
        if (data!=null){
            resultData = new String[4];
            int temp = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i]==null|| data[i].equals("")){
                    temp = 0;
                }else {
                    temp = saveProjectInfo(data[i]);
                }
                resultData[i] = getint2HexString(temp);
                Log.e(TAG, "data[" + i + "]=" + data[i]
                        + ",resultData[" + i + "]=" + resultData[i]
                        + ",temp="+ temp);
            }

        }
        return resultData;
    }
    public String getDataFromDB(String data){
        Log.e(TAG,"getDataFromDB,data="+data);
        String resultData = null;
        if (data!=null){
            int temp = 0;
                if (data==null|| data.equals("")){
                    temp = 0;
                }else {
                    temp = saveProjectInfo(data);
                }
                resultData = getint2HexString(temp);
                Log.e(TAG, "data=" + data
                        + ",resultData=" + resultData
                        + ",temp="+ temp);

        }
        return resultData;
    }

    private String getint2HexString(int data){
        Log.e(TAG,"getHexString,data="+data);
        try {
            return String.format("%08X", data);
        }catch (Exception e){
            Log.e(TAG,"getint2HexString,e="+e);
        }
        return null;
    }

    private int getHexString2int(String data){
        try {
            return Integer.parseInt(data,16);
        }catch (Exception e){
            Log.e(TAG,"getHexString2int,e="+e);
        }
        return 0;
    }

    /*
      public Map<Integer, Gift> getGiftList() {
        L.e(TAG, "getGiftList()");
        if (giftMap == null) {
            giftMap = liveModel.getGiftList();
        }

        // return a empty non-null object to avoid app crash
        if (giftMap == null) {
            giftMap = new HashMap<>();
            return new HashMap<Integer, Gift>();
        }

        return giftMap;
    }
    */

    /*-----------------------获取礼物列表----------------------------------------*/
    /*
    public List<Gift> getGiftLists(){
        if(giftList==null){
            giftList = new ArrayList<>();
            //数据库有礼物,遍历
            if(getGiftList().size()>0){
                Iterator<Map.Entry<Integer, Gift>> iterator = giftMap.entrySet().iterator();
                while (iterator.hasNext()){
                    giftList.add(iterator.next().getValue());
                }
                //排序
                Collections.sort(giftList, new Comparator<Gift>() {
                    @Override
                    public int compare(Gift o1, Gift o2) {
                        return o1.getGprice()-o2.getGprice();
                    }
                });
            }
        }

        return giftList;
    }
    */
}
