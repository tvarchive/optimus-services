package com.testvagrant.services.controllers;

import com.mongodb.gridfs.GridFSFile;
import com.testvagrant.services.entities.Screenshot;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
}
