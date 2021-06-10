package com.example.videolaunching.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.videolaunching.database.SubjectDBDAO;
import com.example.videolaunching.model.Subject;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private ArrayList<String> subjectArrayList;
    private SubjectDBDAO subjectDAO;

    public HomeViewModel(Context context) {

        subjectArrayList = new ArrayList<>();
        subjectDAO = new SubjectDBDAO(context);

    }

    public ArrayList<String> getSubjectList()
    {
         subjectArrayList = subjectDAO.GetAllSubjects();
         return subjectArrayList;
    }


}