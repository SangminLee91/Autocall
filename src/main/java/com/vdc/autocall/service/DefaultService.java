package com.vdc.autocall.service;


import com.vdc.autocall.dao.DefaultDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service("defaultService")
public class DefaultService {
    @Resource(name="defaultDao")
    private DefaultDao defaultDao;

    public List<Map<String, Object>> DeviceList(Map<String, Object> map) throws Exception {
        return defaultDao.DeviceList(map);
    }
    public void addDevice(Map<String, Object> map) throws Exception {
        defaultDao.AddDevice(map);
    }
    public Object editDevice(Map<String, Object> map) throws Exception {
        return defaultDao.EditDevice(map);
    }
    public void delDevice(Map<String, Object> map) throws Exception {
        defaultDao.DelDevice(map);
    }

}
