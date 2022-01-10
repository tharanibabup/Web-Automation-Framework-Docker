package com.docker.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public  class dockerController {

    //private String startDocker_Cmd_Mac = "cmd /c start sh dockerUp.sh";
    private String[] startDocker_Cmd_Mac = {"sh",  "dockerUp.sh", "\\"};
    private String startDocker_Cmd_Win = "cmd /c start dockerUp.bat";
    //private String stopDocker_Cmd_Mac = "cmd /c start sh dockerDown.sh";
    private String[] stopDocker_Cmd_Mac = {"sh",  "dockerDown.sh", "\\"};
    private String stopDocker_Cmd_Win = "cmd /c start dockerDown.bat";
    private String[] scaleDocker_Cmd_Mac = {"sh",  "dockerSaleUp.sh", "\\"};
    private String scaleDocker_Cmd_Win = "cmd /c start dockerSaleUp.bat";


    public String getOperatingSystem() {
        String os = System.getProperty("os.name");
        // System.out.println("Using System Property: " + os);
        return os;
    }

//    public String getStartDockerCmd() {
//        String cmdName = "";
//        if (getOperatingSystem().contains("Mac")) {
//            cmdName = startDocker_Cmd_Mac;
//        } else if (getOperatingSystem().contains("Mac")) {
//            cmdName = startDocker_Cmd_Win;
//        }
//        return cmdName;
//    }
//
//    public String getStopDockerCmd() {
//        String cmdName = "";
//        if (getOperatingSystem().contains("Mac")) {
//            cmdName = stopDocker_Cmd_Mac;
//        } else if (getOperatingSystem().contains("Windows")) {
//            cmdName = stopDocker_Cmd_Win;
//        }
//        return cmdName;
//    }

    public void startDocker() throws IOException, InterruptedException {

        boolean flag = false;
        Runtime runtime = Runtime.getRuntime();
        //System.out.println(getStartDockerCmd());
       // runtime.exec(getStartDockerCmd());

        if (getOperatingSystem().contains("Mac")) {
            runtime.exec(startDocker_Cmd_Mac);
        } else if (getOperatingSystem().contains("Windows")) {
            runtime.exec(startDocker_Cmd_Win);
        }

        String f = "output.txt";

        Calendar cal = Calendar.getInstance();//2:44 15th second
        cal.add(Calendar.SECOND, 45);//2:44   45seconds
        long stopnow = cal.getTimeInMillis();
        Thread.sleep(3000);

        while (System.currentTimeMillis() < stopnow) {
            if (flag) {
                break;
            }

            BufferedReader reader = new BufferedReader(new FileReader(f));
            String currentLine = reader.readLine();
            while (currentLine != null && !flag) {

                if (currentLine.contains("registered to the hub and ready to use")) {
                    System.out.println("found my text");
                    flag = true;//14th seconds
                    break;
                }

                currentLine = reader.readLine();
            }
            reader.close();

        }

        // Assert.assertTrue(flag);
     //   runtime.exec("cmd /c start scale.bat");
      //  Thread.sleep(15000);
        if (getOperatingSystem().contains("Mac")) {
            runtime.exec(scaleDocker_Cmd_Mac);
        } else if (getOperatingSystem().contains("Windows")) {
            runtime.exec(scaleDocker_Cmd_Win);
        }

        Thread.sleep(15000);

    }

    public void stoptDocker() throws IOException, InterruptedException {

        boolean flag=false;
        Runtime runtime= Runtime.getRuntime();
       // runtime.exec(getStopDockerCmd());
        if (getOperatingSystem().contains("Mac")) {
            runtime.exec(startDocker_Cmd_Mac);
        } else if (getOperatingSystem().contains("Windows")) {
            runtime.exec(startDocker_Cmd_Win);
        }

        String f ="output.txt";

        Calendar cal=Calendar.getInstance();//2:44 15th second
        cal.add(Calendar.SECOND, 45);//2:44   45seconds
        long stopnow=cal.getTimeInMillis();
        Thread.sleep(3000);

        while(System.currentTimeMillis()<stopnow)
        {
            if(flag)
            {
                break;
            }

            BufferedReader reader=new BufferedReader(new FileReader(f));
            String currentLine=reader.readLine();
            while(currentLine!=null && !flag)

            {

                if(currentLine.contains("selenium-hub exited"))
                {
                    System.out.println("found my text");
                    flag=true;//14th seconds
                    break;
                }

                currentLine=reader.readLine();
            }
            reader.close();

        }

      //  Assert.assertTrue(flag);
    }

}
