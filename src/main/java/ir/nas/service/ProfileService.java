package ir.nas.service;

import java.util.List;

import ir.nas.exception.repository.ModelNotFoundException;
import ir.nas.model.Profile;
import ir.nas.repository.profile.ProfileRepository;
import ir.nas.repository.profile.ProfileRepositoryImpl;
// import ir.nas.util.Validation;

public final class ProfileService
{
    private final ProfileRepository pRepository;

    public ProfileService(final ProfileRepositoryImpl pRepository)
    {
        this.pRepository = pRepository;
    }

    private final void validateProfile(final Profile profile)
    {
        final int BIOGRAPHY_LENGTH = 500;
        // Validation.of()
        //         .requireNotNull(profile)
        //         .requireTrueLength(profile.getBiography(), BIOGRAPHY_LENGTH)
        //         .requireString(profile.getEmail())
        //         .requireString(profile.getUsername())
        //         .requireString(profile.getPassword())
        //         .validate();
    }

    private final void validateProfileId(final Long id)
    {
        // Validation.of()
        //         .requireNotNegative(id)
        //         .validate();
    }

    public final Long addProfile(final Profile profile)
    {
        this.validateProfile(profile);
        return this.pRepository.create(profile);
    }

    public final Profile findProfileById(final Long id)
    {
        this.validateProfileId(id);
        return this.pRepository.read(id).orElseThrow(() -> {
            throw new ModelNotFoundException("Profile Not Found By This Id [%d]".formatted(id));
        });
    }

    public final Profile updateProfile(final Profile auhtor)
    {
        this.validateProfile(auhtor);
        return this.pRepository.update(auhtor);
    }

    public final boolean deleteProfile(final Long id)
    {
        this.validateProfileId(id);
        return this.pRepository.delete(id);
    }

    public final List<Profile> findAllProfile()
    {
        return this.pRepository.findAll();
    }

    public final Profile findAuthorByWebsite(final String website)
    {
        return this.pRepository.findByWebsite(website).orElseThrow(() -> {
            throw new ModelNotFoundException("Profile Not Found By This Website [%s]".formatted(website));
        });
    }
}
