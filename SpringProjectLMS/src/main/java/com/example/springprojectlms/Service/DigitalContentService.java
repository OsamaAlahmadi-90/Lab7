package com.example.springprojectlms.Service;


import com.example.springprojectlms.Model.DigitalContent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class DigitalContentService {

    ArrayList<DigitalContent> digitalContentList = new ArrayList<>();

    public ArrayList<DigitalContent> getDigitalContents() {
        return digitalContentList;
    }

    public void addDigitalContent(DigitalContent digitalContent) {
        digitalContentList.add(digitalContent);
    }

    public boolean updateDigitalContent(DigitalContent digitalContent, String id) {
        for (int i = 0; i < digitalContentList.size(); i++) {
            if (digitalContentList.get(i).getId().equals(id)) {
                digitalContentList.set(i, digitalContent);
                return true;
            }
        }
        return false;
    }

    public boolean deleteDigitalContent(String id) {
        for (DigitalContent d : digitalContentList) {
            if (d.getId().equals(id)) {
                digitalContentList.remove(d);
                return true;
            }
        }
        return false;
    }

    public DigitalContent getContentById(String id) {
        for (DigitalContent d : digitalContentList) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        return null;
    }

    public ArrayList<DigitalContent> getContentsByAuthor(String author) {
        ArrayList<DigitalContent> contentsByAuthorList = new ArrayList<>();
        for (DigitalContent d : digitalContentList){
            if (d.getAuthor().equalsIgnoreCase(author)){
                contentsByAuthorList.add(d);
            }
        }
        return contentsByAuthorList;

    }

    public ArrayList<DigitalContent> getByCategoryAndTextLength(String category,int length){
        ArrayList<DigitalContent> contentsByCategoryAndLengthList = new ArrayList<>();

        for (DigitalContent d : digitalContentList){
            if(d.getCategory().equalsIgnoreCase(category) && d.getContentText().length() >= length){
                contentsByCategoryAndLengthList.add(d);
            }
        }

        return contentsByCategoryAndLengthList;
    }


    public ArrayList<DigitalContent> getContentsAfterGivenDate(LocalDate date){
        ArrayList<DigitalContent> contentAfterGivenDateList = new ArrayList<>();

        for (DigitalContent d: digitalContentList){
            if(date.isAfter(d.getPublishDate())){
                contentAfterGivenDateList.add(d);
            }
        }

        return contentAfterGivenDateList;
    }

}