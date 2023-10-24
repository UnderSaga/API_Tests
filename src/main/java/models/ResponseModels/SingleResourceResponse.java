package models.ResponseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleResourceResponse {
    public DataResourceResponse data;
    public SupportResponse support;
}
