package com.example.template.service;

import com.example.template.domain.Melodie;
import com.example.template.repo.SQLRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private SQLRepo repo;

    public Service(SQLRepo repo) {
        this.repo = repo;
    }

    public ArrayList<Melodie> getAll() {
        return repo.getAll();
    }

    public int getId() {
        if (repo.getAll().size() == 0) {
            return 100;
        }
        return repo.getAll().get(repo.getAll().size() - 1).getId() + 1;
    }

    public void add(int id, String formatie, String titlu, String gen, String durata) {
        Melodie m = new Melodie(id,formatie,titlu,gen,durata);
        repo.add(m);
    }
    public List<Melodie> filtreaza( String substring) {
        List<Melodie> filteredSongs = new ArrayList<>();

        for (Melodie song : getAll()) {
            if (containsSubstringIgnoreCase(song.getFormatie(), substring) ||
                    containsSubstringIgnoreCase(song.getTitlu(), substring) ||
                    containsSubstringIgnoreCase(song.getGen(), substring) ||
                    containsSubstringIgnoreCase(song.getDurata(), substring)) {
                filteredSongs.add(song);
            }
        }
        return filteredSongs;
    }

    // Helper method to check if a string contains a substring (case-insensitive)
    private static boolean containsSubstringIgnoreCase(String input, String substring) {
        return input != null && input.toLowerCase().contains(substring.toLowerCase());
    }

    public List<Melodie> getRandomSongs() {
        List<Melodie> cumulativeSongs = new ArrayList<>();
        List<Melodie> allSongs = getAll();

        // Shuffle the list of songs randomly
        List<Melodie> shuffledSongs = new ArrayList<>(allSongs);
        Collections.shuffle(shuffledSongs);

        int totalDurationMinutes = 0;

        for (Melodie song : shuffledSongs) {
            int songDurationMinutes = convertDurationToMinutes(song.getDurata());

            // Check if adding the current song exceeds the 15-minute limit
            if (totalDurationMinutes + songDurationMinutes <= 15) {
                cumulativeSongs.add(song);
                totalDurationMinutes += songDurationMinutes;
            } else {
                // If adding the current song exceeds the limit, break the loop
                break;
            }
        }

        // Shuffle the cumulative list for better randomness
        Collections.shuffle(cumulativeSongs);

        return cumulativeSongs;
    }

    // Helper method to convert "hh:mm:ss" duration to minutes
    private int convertDurationToMinutes(String duration) {
        String[] parts = duration.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return hours * 60 + minutes + seconds / 60;
    }


//    public int findById(int id) {
//        return repo.findById(id);
//    }
}