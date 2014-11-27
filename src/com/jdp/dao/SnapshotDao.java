package com.jdp.dao;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
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
    
    public List<SnapshotData> getAllSnapshotDatas() {
    	
        List<SnapshotData> SnapshotDatas = new ArrayList<SnapshotData>();
        
		Objectify ofy = ObjectifyService.begin();
		Query<SnapshotData> query = ofy.query(SnapshotData.class);
		
		for (SnapshotData snapshotdataFromQuery : query) {
			SnapshotDatas.add(snapshotdataFromQuery);
		}
		
        return SnapshotDatas;
    }
    

}
