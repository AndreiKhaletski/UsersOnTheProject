package by.it_academy.service.core.pageofsize;

import by.it_academy.service.core.ProjectAndUsersDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PageOfProjectAndUsersDto {
    private Integer number;
    private Integer size;
    private Integer total_pages;
    private Long total_elements;
    private boolean first;
    private Integer number_of_elements;
    private boolean last;
    private List<ProjectAndUsersDto> content;
}
