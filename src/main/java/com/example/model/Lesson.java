package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private int id;
    private String name;
    private int duration;
    private String lecturerName;
    private double price;
}
