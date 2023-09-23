package com.cloudinary.rest.controllers.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FileDTO {
    private String public_id;
    private String type;
    private int bytes;
    private int width;
    private int height;
    private String created_at;
    private String secure_url;
    private String url;
    private String original_filename;
    private String format;
    private String resource_type;
}
