package com.jdp.dao;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.jdp.model.SnapshotData;

public class SnapshotDao {
	
    public SnapshotDao() {
    	ObjectifyService.register(SnapshotData.class);
    }

    public void addSnapshotData(SnapshotData SnapshotData) {
    	Objectify obj = ObjectifyService.beginTransaction();
        obj.put(SnapshotData);
        obj.getTxn().commit();
    }

}
