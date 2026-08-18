package ir.nas.service;

import java.util.List;

import ir.nas.exception.repository.ModelNotFoundException;
import ir.nas.model.Profile;
import ir.nas.repository.RepositoryFactory;
import ir.nas.repository.profile.ProfileRepository;

public final class ProfileService
{
    private final ProfileRepository pRepository;

    public ProfileService(final RepositoryFactory repositoryFactory)
    {
        this.pRepository = repositoryFactory.getInstance();
    }

    public final Long addProfile(final Profile profile)
    {
        return this.pRepository.create(profile);
    }

    public final Profile findProfileById(final Long id)
    {
        return this.pRepository.read(id).orElseThrow(() -> {
            throw new ModelNotFoundException("Profile Not Found By This Id [%d]".formatted(id));
        });
    }

    public final Profile updateProfile(final Profile auhtor)
    {
        return this.pRepository.update(auhtor);
    }

    public final boolean deleteProfile(final Long id)
    {
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
