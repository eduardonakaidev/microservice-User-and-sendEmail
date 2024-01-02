package com.ms.msemail.dtos;

import java.util.UUID;

public record EmailRecordDto (UUID userId,String emailto,String subject,String text){
    
}
