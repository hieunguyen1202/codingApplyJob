package com.swp.server.controller;

import com.swp.server.dto.Favorite_JobDTO;
import com.swp.server.repository.FavoriteJobRepo;
import com.swp.server.services.favorite_job.FavoriteJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/favorite")
public class FavoriteJobController {

    @Autowired
    private FavoriteJobService favoriteJobService;

    @PostMapping("/favoritejob")
    public ResponseEntity<?> favoriateJob(@ModelAttribute Favorite_JobDTO favorite_jobDTO){
        return favoriteJobService.favoriteJob(favorite_jobDTO);
    }
    @PostMapping("/viewfavoritejob")
    public ResponseEntity<?> viewFavoriateJob(@RequestBody int accountId){
        return favoriteJobService.viewFavoriteJob(accountId);
    }   @DeleteMapping("/deletefavoritejob/{favoriteJobId}")
    public ResponseEntity<?> deleteFavoriteJob(@PathVariable int favoriteJobId){
        return favoriteJobService.deleteFavoriteJob(favoriteJobId);
    }
}
