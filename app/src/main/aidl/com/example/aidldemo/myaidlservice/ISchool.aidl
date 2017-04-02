// ISchool.aidl
package com.example.aidldemo.myaidlservice;

// Declare any non-default types here with import statements
import com.example.aidldemo.myaidlservice.Student;

interface ISchool {
   String getSchoolName();
   int getStudentNum();

   Student getStudent();
}

