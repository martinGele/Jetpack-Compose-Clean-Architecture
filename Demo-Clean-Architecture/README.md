### A test Demo Application done for JobAndTalent as a technical assignment 

### Job-And-Talent Architecture:


All the layers in CLEAN architecture has been separated into individual modules in a single Android project. For example android modules like `app`, `presentation`, `data` and `domain`. Notice the dependency of these modules, as per the depedency rule of CLEAN architecture, all the dependencies directly or indirectly point towards the the domain layer. The domain layer incorporates `Entities`, `Use-cases` and interfaces required to cross boundaries, `Repository` in this case. The `Data` layer handles data and communicates with data source `remote` in this case, to provides required data requested by the `presentation` layer. `View` layer will observe on the `presentation` layer's stateFlow object, to get the updated data on state change.
