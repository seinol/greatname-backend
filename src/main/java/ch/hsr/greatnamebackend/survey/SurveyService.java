package ch.hsr.greatnamebackend.survey;

import ch.hsr.greatnamebackend.answer.Answer;
import ch.hsr.greatnamebackend.answer.AnswerService;
import ch.hsr.greatnamebackend.person.Person;
import ch.hsr.greatnamebackend.person.PersonService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@GraphQLApi
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final AnswerService answerService;
    private final PersonService personService;

    public SurveyService(SurveyRepository surveyRepository, AnswerService answerService, PersonService personService) {
        this.surveyRepository = surveyRepository;
        this.answerService = answerService;
        this.personService = personService;
    }

    @GraphQLQuery(name = "surveys")
    public List<Survey> getSurveys() {
        return surveyRepository.findAll();
    }

    @GraphQLQuery(name = "survey")
    public Optional<Survey> getSurveyById(@GraphQLArgument(name = "id") Integer id) {
        return surveyRepository.findById(id);
    }

    @GraphQLQuery(name = "participants")
    public List<Person> getParticipants(@GraphQLContext Survey survey) {
        return personService.getSurveyParticipants(survey);
    }
}