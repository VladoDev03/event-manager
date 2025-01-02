package com.example.SpringProject.Creator;

import com.example.SpringProject.Creator.Dto.CreatorDto;
import com.example.SpringProject.User.User;
import com.example.SpringProject.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreatorService {
    private final CreatorRepository creatorRepository;
    private final UserRepository userRepository;

    @Autowired
    public CreatorService(CreatorRepository creatorRepository, UserRepository userRepository) {
        this.creatorRepository = creatorRepository;
        this.userRepository = userRepository;
    }

    public List<CreatorDto> getCreators(){
        return creatorRepository.findAll().stream().map(creator -> new CreatorDto(creator.getFirstName()))
                .collect(Collectors.toList());
    }

    public void saveCreator(Creator creator){
        creatorRepository.save(creator);
    }

    public CreatorDto getCreatorById(Long id){
        Creator creator = creatorRepository.findById(id).orElse(null);

        if (creator != null){
            return new CreatorDto(creator.getFirstName());
        }
        return null;
    }
}
