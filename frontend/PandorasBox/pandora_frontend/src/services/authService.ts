import { Auth } from "aws-amplify"

export async function login(email: string, password: string) {
    await Auth.signIn(email, password);
    const session = await Auth.currentSession();
    const idToken = session.getIdToken().getJwtToken();

    localStorage.setItem("idToken", idToken);

    return idToken;
}