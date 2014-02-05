bridges
=======

TUES Bridges Project - uniting classrooms for better student engagement

## Getting Started

### As a Student

- Download the example project for follower graphs (incomplete)
- Contains an existing Eclipse project, with maven configuration
  - You should use the eclipse-m2e plugin or similar to keep dependencies up to date

> If you decide to develop using your own environment, add the following dependency:
> group: "edu.uncc.cs.bridges", artifact: "client-scala", version: "0.2"

### As a Developer

- Clone this repository: `git clone https://github.com/SeanTater/bridges.git`
- Install [Gradle](http://gradle.org)
- Add gradle binaries to your PATH
  - on *nix: add this to .bashrc, .zshrc, or shell configuration of your choice:
  - `PATH=$PATH:/path/to/gradle/bin`
- One step from nothing to working code with running tests:
  - `gradle test`
