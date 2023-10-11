package pl.kwec.apartmentreservationsystem.user.model;

public record UserRequest(String email, String password, String name, String surname) {
}