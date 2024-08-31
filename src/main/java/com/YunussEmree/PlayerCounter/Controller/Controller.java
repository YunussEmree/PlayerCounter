package com.YunussEmree.PlayerCounter.Controller;

import com.YunussEmree.PlayerCounter.Database.MySqlHelper;
import com.YunussEmree.PlayerCounter.PlayerRecord;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class Controller {

    private MySqlHelper mySqlHelper;

    @PostMapping("/postdata")
    public void postData(@RequestBody PlayerRecord playerRecord) {
        mySqlHelper.saveData(playerRecord.getCount(), playerRecord.getTime());
    }

    @GetMapping("/getdata/{limit}")
    public HashMap<String,Integer> getData(@PathVariable("limit") int limit) {

        HashMap<String, Integer> data = new HashMap<>();

        try {
            while (mySqlHelper.getDataFromDB(limit).next()) {
                data.put(mySqlHelper.getDataFromDB(limit).getString("time"), mySqlHelper.getDataFromDB(limit).getInt("count"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
