package com.example.SpringProject.Creator;

import com.example.SpringProject.Creator.Dto.CreatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreatorService {

    public List<CreatorDto> getCreators(){
        return CreatorDao.getCreators().stream().map(creator -> new CreatorDto(creator.getFirstName()))
                .collect(Collectors.toList());
    }

    public void saveCreator(Creator creator){
        CreatorDao.createCreator(creator);
    }

    public CreatorDto getCreatorById(Long id){
        Creator creator = CreatorDao.getCreatorById(id);

        if (creator != null){
            return new CreatorDto(creator.getFirstName());
        }
        return null;
    }
}
