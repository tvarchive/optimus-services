package com.testvagrant.services.controllers;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.testvagrant.services.entities.Screenshot;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
public class ScreenshotController {


    private GridFsOperations gridFsOperations;

    public ScreenshotController(GridFsOperations gridFsOperations) {
        this.gridFsOperations = gridFsOperations;
    }

    @RequestMapping("/screenshots")
    public String storeScreenshot(@RequestBody Screenshot screenshot) {
        InputStream inputStream = new ByteArrayInputStream(screenshot.getData());
        GridFSFile store = gridFsOperations.store(inputStream, screenshot.getFileName());
        return store.getFilename();
    }

    @DeleteMapping("/screenshots/delete")
    public String deleteScreenshot() {
        Query query = new Query();
        query.addCriteria(Criteria.where("uploadDate").lt(new Date()));
        System.out.println(query);
        List<GridFSDBFile> gridFSDBFiles = gridFsOperations.find(query);
        gridFsOperations.delete(query);
        return String.valueOf(gridFSDBFiles.size());
    }
}
