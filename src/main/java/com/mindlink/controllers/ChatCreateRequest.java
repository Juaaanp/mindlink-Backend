package com.mindlink.controllers;

import lombok.Data;
import java.util.List;

@Data
public class ChatCreateRequest {
    private List<String> participantEmails; 
}