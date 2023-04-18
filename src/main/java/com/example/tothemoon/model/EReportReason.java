package com.example.tothemoon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;



public enum EReportReason {
    BREAKES_RULES,HARASSMENT,HATE,SHARING_PERSONAL_INFO,IMPERSONATION,COPYRIGHT_VIOLATION,
    TRADEMARK_VIOLATION,SPAM,SELF_HARM_OR_SUICIDE,OTHER;

}
