package com.example.SpringProject.Creator;

import com.example.SpringProject.Creator.Dto.CreatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/creator")
public class CreatorController {
    private final CreatorService creatorService;

    @Autowired
    public CreatorController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    @GetMapping
    public List<CreatorDto> getCreators(){
        return creatorService.getCreators();
    }

    @PostMapping
    public void saveCreator(@RequestBody Creator creator){
        creatorService.saveCreator(creator);
    }

    @GetMapping("/{id}")
    public CreatorDto getCreatorById(@PathVariable Long id){
        return creatorService.getCreatorById(id);
    }
}
