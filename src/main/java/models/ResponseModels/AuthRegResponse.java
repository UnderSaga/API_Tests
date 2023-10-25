package models.ResponseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegResponse {
    public Integer id;
    public String token;
    public String error;
}
