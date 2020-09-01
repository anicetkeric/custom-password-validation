import { ApiErrorMessagePipe } from './api-error-message.pipe';

describe('ApiErrorMessagePipe', () => {
  it('create an instance', () => {
    const pipe = new ApiErrorMessagePipe();
    expect(pipe).toBeTruthy();
  });
});
