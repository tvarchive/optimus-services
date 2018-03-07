package com.testvagrant.services.controllers;

import com.mongodb.client.gridfs.GridFSFindIterable;
import com.testvagrant.services.entities.Screenshot;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

@RestController
public class ScreenshotController {


    private GridFsOperations gridFsOperations;

    public ScreenshotController(GridFsOperations gridFsOperations) {
        this.gridFsOperations = gridFsOperations;
    }

    @RequestMapping("/screenshots")
    public String storeScreenshot(@RequestBody Screenshot screenshot) {
        InputStream inputStream = new ByteArrayInputStream(screenshot.getData());
        gridFsOperations.store(inputStream, screenshot.getFileName());
        return screenshot.getFileName();
    }

    @DeleteMapping("/screenshots/delete")
    public String deleteScreenshot() {
        Query query = new Query();
        query.addCriteria(Criteria.where("uploadDate").lt(new Date()));
        System.out.println(query);
        GridFSFindIterable gridFSDBFiles = gridFsOperations.find(query);
        gridFsOperations.delete(query);
        return String.valueOf(gridFSDBFiles.spliterator().getExactSizeIfKnown());
    }
}
