
export interface IUserData {
    username?: string;
    email?: string;
    password?: string;
    confirmPassword?: string;
}

export class UserData implements IUserData {
    constructor(
        public username?: string,
        public email?: string,
        public password?: string,
        public confirmPassword?: string,
    ) { }
}
