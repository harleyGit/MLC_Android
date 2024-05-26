package com.ganghuang.mlc2_android.TestModules;

import android.os.AsyncTask;
import android.widget.Toast;

public class TestDownloadTask extends AsyncTask<Void, Integer, Boolean> {

    /**
     * 这个方法会在后台任务开始执行之前调用，用于进行一些界面上的初始化操作，比如显示一个进度条对话框等。
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    /**
     * 这个方法中的所有代码都会在子线程中运行，我们应该在这里去处理所有的耗时任务。
     * 任务一旦完成就可以通过return语句来将任务的执行结果返回，如果AsyncTask的第三个泛型参数指定的是Void，就可以不返回任务执行结果。
     * 注意，在这个方法中是不可以进行UI操作的，如果需要更新UI元素，比如说反馈当前任务的执行进度，可以调用publishProgress (Progress...)方法来完成。
     *
     * **/
    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            while (true){
                int downloadPercent = doDownload();//这是一个虚构的函数
                publishProgress(downloadPercent);
                if (downloadPercent >= 100){
                    break;
                }
            }
        }catch (Exception e){
            return  false;
        }
        return true;
    }

    private int doDownload(){
        return 100;
    }


    /**
     * 当在后台任务中调用了publishProgress(Progress...)方法后，onProgressUpdate(Progress...)方法就会很快被调用，
     * 该方法中携带的参数就是在后台任务中传递过来的。在这个方法中可以对UI进行操作，利用参数中的数值就可以对界面元素进行相应的更新。
     * */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        //更新下载进度
        //progressDialog.setMessage("Downloaded"+ values[0]);
    }


    /**
     * 当在后台任务中调用了publishProgress(Progress...)方法后，onProgressUpdate(Progress...)方法就会很快被调用，该方法中携带的参数就是在后台任务中传递过来的。
     * 在这个方法中可以对UI进行操作，利用参数中的数值就可以对界面元素进行相应的更新。
     * */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        //progressDialog.dismiss();//关闭对话框
        if (result){
           // Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show();
        }else {
            //Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
        }
    }


}
