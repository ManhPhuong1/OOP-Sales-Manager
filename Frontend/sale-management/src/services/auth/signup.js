import request from '../../utils/httpRequest';

export const signup = async ({ phone, password }) => {
    console.log("signup phone", phone, password);
    try {
        const res = await request.post('auth/register', { phone, password });

        return res.data;
    } catch (err) {
        console.log("err", err.response);
        return err.response;
    }
};
